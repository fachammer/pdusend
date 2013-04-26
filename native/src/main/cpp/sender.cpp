/*
 * pdusender.c
 *
 *  Created on: Apr 7, 2013
 *      Author: fabian
 */
#include <stdio.h>
#include <errno.h>
#include <sys/socket.h>
#include <linux/if_packet.h>
#include <netinet/ether.h>
#include <netinet/in.h>
#include <unistd.h>
#include "sender.h"

JNIEXPORT void JNICALL Java_at_fabianachammer_pdusend_Sender_send(
		JNIEnv * env, jobject obj, jint interfaceId, jbyteArray data) {

	int socketDomain = AF_PACKET;
	int protocol = htons(ETH_P_ALL);
	int socketType = SOCK_RAW;
	int socketFd = socket(socketDomain, socketType, protocol);

	if (socketFd == -1) {
		switch (errno) {
		case 1:
			(*env).ThrowNew((*env).FindClass("java/net/SocketException"),
					"permission denied");
			return;
		}
	}

	struct sockaddr_ll sourceAddress;

	sourceAddress.sll_family = socketDomain;
	sourceAddress.sll_ifindex = interfaceId;

	int bindResult = bind(socketFd, (struct sockaddr *) &sourceAddress,
			sizeof(struct sockaddr_ll));

	if (bindResult == -1) {
		(*env).ThrowNew((*env).FindClass("java/net/BindException"),
				"binding was not possible");
		return;
	}

	jbyte *dataPointer;
	dataPointer = (*env).GetByteArrayElements(data, NULL );
	int dataLength = (*env).GetArrayLength(data);

	int sendResult = write(socketFd, dataPointer, dataLength);

	if (sendResult == -1) {
		(*env).ThrowNew((*env).FindClass("java/net/SocketException"),
				"sending was not possible");

	}

	(*env).ReleaseByteArrayElements(data, dataPointer, 0);
	return;
}
