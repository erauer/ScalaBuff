package hr.sandrogrzicic.scalabuff

import collection.mutable.{ListBuffer, StringBuilder}
import annotation.tailrec
import java.io._

/**
 * Scala class generator.
 * @author Sandro Gržičić
 */

class Generator protected(sourceName: String, reader: Reader) {

	implicit def buffString(string: String): BuffedString = new BuffedString(string)

	val imports = ListBuffer[String]()

	var packageName: String = ""
	var className: String = sourceName.takeUntilFirst('.').camelCase

	/**
	 * Whether to optimize the resultant class for speed (true) or for code size (false). True by default.
	 */
	var optimizeForSpeed = true

	/**
	 * Generates the Scala class code.
	 */
	protected def generate(tree: List[Node]) = {


		// *******************
		//   utility methods
		// *******************

		/**
		 * Enum generation
		 */
		def enum(enum: EnumStatement, indentLevel: Int = 0) = {
			val indentOuter = BuffedString.indent(indentLevel + 1)
			val indent = indentOuter + "\t"

			val out = StringBuilder.newBuilder
			out
				.append(indentOuter).append("object ").append(enum.name).append(" extends hr.sandrogrzicic.scalabuff.runtime.Enum {\n")
				.append(indent).append("sealed trait EnumVal extends Value\n")
				.append(indent).append("\t\n")

			for (enumOption <- enum.options) {
				// options?
			}
			// declaration of constants
			for (const <- enum.constants) {
				out.append(indent)
					.append("val ").append(const.name).append(" = new EnumVal { ")
					.append("val name = \"").append(const.name).append("\"; ")
					.append("val id = ").append(const.id)
					.append(" }\n")
			}
			out.append("\n")
			// constants, as statics
			for (const <- enum.constants) {
				out.append(indent).append("val ").append(const.name).append("_VALUE = ").append(const.id).append("\n")
			}

			// valueOf
			out.append("\n").append(indent).append("def valueOf(id: Int) = ")
			if (optimizeForSpeed) {	// O(1)
				out.append("(id: @annotation.switch) match {\n")
				for (const <- enum.constants) {
					out.append(indent).append("\t")
						.append("case ").append(const.id).append(" => ").append(const.name).append("\n")
				}
				out.append(indent).append("}\n")
			} else {	// O(n)
				out.append("values.find(_.id == id).getOrElse(null)\n")
			}

			// internalGetValueMap
			out.append(indent).append("val internalGetValueMap = new com.google.protobuf.Internal.EnumLiteMap[EnumVal] {\n")
				.append(indent).append("\tdef findValueByNumber(id: Int): EnumVal = valueOf(id)\n")
				.append(indent).append("}\n")

			out.append(indentOuter).append("}\n")

			out.mkString
		}

		/**
		 * Message generation, recurses for nested messages.
		 * Not tail-recursive, but shouldn't cause stack overflows on sane nesting levels.
		 */
		def message(name: String, body: MessageBody, indentLevel: Int = 0): String = {
			val indent0 = BuffedString.indent(indentLevel + 1)
			val indent1 = indent0 + "\t"
			val indent2 = indent1 + "\t"
			val indent3 = indent2 + "\t"

			val fields = body.fields

			/**
			 * Returns the field ID based on the field number.
			 */
			def fieldID(fieldNumber: Int) = {
				0

			}

			body.options.foreach {
				case Option(key, value) => // ignored: no options supported yet
			}
			body.extensionRanges.foreach {
				case ExtensionRanges(extensionRanges) => // not supported yet
			}


			val out = StringBuilder.newBuilder

			// class
			out
				.append(indent0).append("final class ").append(name).append(" private (\n")
			fields.foreach { field =>
			    out.append(indent1).append("private var _").append(field.name.lowerCamelCase).append(": ")
				if (field.label == FieldLabels.REPEATED) out.append("Vector[")
				out.append(field.fType.scalaType)
				if (field.label == FieldLabels.REPEATED) out.append("]")
				out.append(" = ")
				field.label match {
					case FieldLabels.REPEATED => out.append("Vector.empty[").append(field.fType.scalaType).append("]")
					case _ => out.append(field.fType.defaultValue)
				}
				out.append(", \n")
			}
			out
				.append(indent1).append("private var setFields: collection.BitSet = collection.BitSet.empty\n")
				.append(indent0).append(") extends com.google.protobuf.GeneratedMessageLite\n")
				.append(indent1).append("with com.google.protobuf.MessageLiteOrBuilder\n")
				.append(indent1).append("with hr.sandrogrzicic.scalabuff.runtime.Message[").append(name).append("] {\n")
			fields.foreach { field =>
				if (field.label != FieldLabels.REPEATED)
					out.append(indent1)
						.append("def has").append(field.name.camelCase)
						.append(" = setFields.contains(").append(fieldID(field.number)).append(")\n")
			}
			out.append(indent0).append("}\n\n")

			// object
			out.append(indent0).append("object ").append(name).append(" {\n")

				// apply
				.append(indent1).append("def apply() = defaultInstance\n")
				.append(indent1).append("def apply(message: ").append(name).append(" = defaultInstance.mergeFrom(message)\n")
			if (!fields.isEmpty) {
				out.append(indent1).append("def apply(\n")
				fields.foreach {
					field =>
						out.append(indent2).append("\t").append(field.name.lowerCamelCase).append(": ")
						field.label match {
							case FieldLabels.REQUIRED => out.append(field.fType.scalaType).append(" = ").append(field.fType.defaultValue).append(",\n")
							case FieldLabels.OPTIONAL => out.append("Option[").append(field.fType.scalaType).append("] = None,\n")
							case FieldLabels.REPEATED => out.append("Vector[").append(field.fType.scalaType).append("] = Vector.empty[").append(field.fType.scalaType).append("],\n")
							case _ => // weird warning - missing combination <local child> ?!
						}
				}
				out.length -= 2
				out.append("\n").append(indent1).append(") = {\n")
					.append(indent2).append("val setFields = collection.mutable.BitSet.empty\n")
					.append(indent2).append("new ").append(name).append("(\n")
				fields.foreach {
					field =>
						out.append(indent3).append(field.name.lowerCamelCase)
						if (field.label == FieldLabels.OPTIONAL) out.append(".getOrElse(").append(field.fType.defaultValue).append(")")
						out.append(",\n")
				}
				out.length -= 2
				out.append("\n")
					.append(indent2).append(")\n")
					.append(indent1).append("}\n")
			}
			out
				.append(indent1).append("val defaultInstance = new ").append(name).append("()\n")
				.append(indent1).append("def getDefaultInstance = defaultInstance\n")
				.append("\n")

			body.fields.foreach {  // field number integer constants
				field => out.append(indent1)
					.append("val ").append(field.name.toUpperCase)
					.append("_FIELD_NUMBER = ").append(field.number).append("\n")
			}

//			out
//				.append(indent).append("").append("\n")
//				.append(indent).append("").append("\n")

			out.append("\n")
			// append any nested enums
			body.enums.foreach {
				e => out.append(enum(e, indentLevel + 1)).append("\n")
			}

			// append any nested groups
			body.groups.foreach {
				case Group(label, nestedName, id, nestedBody) => // not supported yet (also, deprecated..)
			}
			// append any nested message extensions
			body.extensions.foreach {
				case Extension(nestedName, nestedBody) => // not supported yet
			}
			// append any nested messages
			body.messages.foreach {
				case Message(nestedName, nestedBody) => out.append(message(nestedName, nestedBody, indentLevel + 1))
			}

			// finalize object
			out.append(indent0).append("}\n")

			out.mkString
		}

		/**
		 * Recursively traverse the tree.
		 */
		@tailrec
		def traverse(tree: List[Node], output: StringBuilder = StringBuilder.newBuilder): String = {
			tree match {
				// if the tree is empty, return the generated output
				case Nil => output.mkString
				// else, build upon the output and call traverse() again with the tree tail
				case node :: tail => node match {
					case Message(name, body) => output.append(message(name, body))
					case Extension(name, body) => // very similar to Message
					case e: EnumStatement => output.append(enum(e))
					case ImportStatement(name) => imports += name
					case PackageStatement(name) => packageName = name
					case Option(key, value) => key match {
						case "java_package" => packageName = value
						case "scala_package" => packageName = value
						case "java_outer_classname" => className = value
						case "scala_outer_classname" => className = value
						case "optimize_for" => value match {
							case "SPEED" => optimizeForSpeed = true
							case "CODE_SIZE" => optimizeForSpeed = false
							case "LITE_RUNTIME" => optimizeForSpeed = true
							case _ => throw new InvalidOptionValueException(key, value)
						}
						case _ => // ignore options which aren't recognized
					}
					case _ => throw new UnexpectedNodeException(node)
				}
				traverse(tail, output)
			}
		}

		// traverse the tree, so we can get class/package names, options, etc.
		val generated = traverse(tree)

		val output = StringBuilder.newBuilder

		// header
		output
			.append("// Generated by ScalaBuff, the Scala protocol buffer compiler. DO NOT EDIT!\n")
			.append("// source: ")
			.append(sourceName)
			.append("\n\n")
		// package
		if (!packageName.isEmpty)
			output.append("package ").append(packageName).append("\n\n")
		// begin outer object
		output.append("object ").append(className).append(" {\n")
		// inner classes, objects etc.
		output.append(generated).append("\n")
		// finalize outer object
		output
			.append("\tdef registerAllExtensions(registry: com.google.protobuf.ExtensionRegistryLite) {\n")
			.append("\t}\n\n")
			.append("}")

		ScalaClass(output.mkString, packageName.replace('.', '/') + '/', className)
	}
}


object Generator {
	/**
	 * Returns a valid Scala class.
	 */
	def apply(tree: List[Node], sourceName: String, sourceReader: Reader): ScalaClass = {
		new Generator(sourceName, sourceReader).generate(tree)
	}

	/**
	 * Returns a valid Scala class.
	 */
	def apply(tree: List[Node], sourceName: String, sourceFile: File): ScalaClass = {
		apply(tree, sourceName, new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), "utf-8")))
	}

}

/**
 * A generated Scala class. The path is relative.
 * @author Sandro Gržičić
 */
case class ScalaClass(body: String, path: String, file: String) {
	assert(path.endsWith("/"), "path must end with a /")
	assert(!file.isEmpty, "file name must not be empty")
	assert(!file.contains("/"), "file name must not contain a /")
}

/**
 * Thrown when a valid Scala class cannot be generated using the the tree returned from the Parser.
 */
class GenerationFailureException(message: String) extends RuntimeException(message)

/**
 * Thrown when a Node occurs in an unexpected location in the tree.
 */
class UnexpectedNodeException(node: Node, parentNode: Node = null) extends GenerationFailureException(
	"Unexpected child node " + node.toString + parentNode match {
		case null => ""
		case _ => "found in " + parentNode.toString
	}
)

class InvalidOptionValueException(key: String, value: String) extends GenerationFailureException(
	"Invalid option value " + value + " for key " + key
)