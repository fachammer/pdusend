package at.fabianachammer.pdusend.dsl;

import org.codehaus.groovy.control.CompilerConfiguration

def vocabulary = new Vocabulary()


def binding = new Binding(vocabulary.binding()+[send: vocabulary.&send,
	on: vocabulary.&on])

def shell = new GroovyShell(binding)
shell.evaluate(new File("src/main/groovy/ExampleScript.groovy"))
