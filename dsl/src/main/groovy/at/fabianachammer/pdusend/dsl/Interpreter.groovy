package at.fabianachammer.pdusend.dsl;

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer


def vocabulary = new Vocabulary()
def binding = new Binding(vocabulary.binding())
def config = new CompilerConfiguration()
def importCustomizer = new ImportCustomizer()

importCustomizer.addImport("at.fabianachammer.pdusend.Sender")
importCustomizer.addStarImport("at.fabianachammer.pdusend.type")
importCustomizer.addStaticStar("at.fabianachammer.pdusend.type.HardwareAddressType")
importCustomizer.addStaticStar("at.fabianachammer.pdusend.type.EtherType")
importCustomizer.addStaticStar("at.fabianachammer.pdusend.type.ArpOperation")

config.addCompilationCustomizers(importCustomizer)

def shell = new GroovyShell(binding, config)
shell.evaluate(new File("src/integration-test/scripts/"+scriptName+".pdusend"))