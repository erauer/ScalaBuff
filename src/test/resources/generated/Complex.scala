// Generated by ScalaBuff, the Scala protocol buffer compiler. DO NOT EDIT!
// source: complex.proto

object Complex {
	trait ComplexMessageOrBuilder extends com.google.protobuf.MessageLiteOrBuilder {

		def hasFirstField: Boolean
		def getFirstField: Int

		def hasSecondField: Boolean
		def getSecondField: String

	}

	case class ComplexMessage() extends com.google.protobuf.GeneratedMessageLite with ComplexMessageOrBuilder {
	}

	object SimpleEnum extends hr.sandrogrzicic.scalabuff.runtime.Enum {
		sealed trait EnumVal extends Value
			
		val KEY_NAME = new EnumVal { val name = "KEY_NAME"; val id = 0 }

		val KEY_NAME_VALUE = 0

		def valueOf(id: Int) = id match {
			case 0 => KEY_NAME
		}

		val internalGetValueMap = new com.google.protobuf.Internal.EnumLiteMap[EnumVal] {
			def findValueByNumber(id: Int): EnumVal = valueOf(id)
		}
	}

		trait NestedOrBuilder extends com.google.protobuf.MessageLiteOrBuilder {

			def hasNestedField: Boolean
			def getNestedField: String

		}

		case class Nested() extends com.google.protobuf.GeneratedMessageLite with NestedOrBuilder {
		}


	def registerAllExtensions(registry: com.google.protobuf.ExtensionRegistryLite) {
	}

}