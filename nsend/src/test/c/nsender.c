/*
 * nsender.c
 *
 *  Created on: 31.03.2013
 *      Author: fabian
 */

#include <sys/socket.h>
#include <netpacket/packet.h>
#include <net/ethernet.h>
#include <linux/if_ether.h>
#include <netinet/in.h>
#include <unistd.h>
#include "nsender.h"

void Java_at_fabianachammer_nsend_NSender_send(JNIEnv *env, jobject obj,
		jint interfaceId, jbyteArray data) {

	struct sockaddr_ll socketAddress;

	socketAddress.sll_family = AF_PACKET;
	socketAddress.sll_protocol = htons(ETH_P_ALL);
	socketAddress.sll_ifindex = interfaceId;

	int sendSocket = socket(socketAddress.sll_family, SOCK_RAW,
			socketAddress.sll_protocol);

	bind(sendSocket, (struct sockaddr *) &socketAddress,
			sizeof(struct sockaddr_ll));

	write(sendSocket, data, sizeof(data));

	close(sendSocket);
}
