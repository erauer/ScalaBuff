// Generated by ScalaBuff, the Scala protocol buffer compiler. DO NOT EDIT!
// source: dataTypes.proto

object DataTypes {
	trait DataTypesOrBuilder extends com.google.protobuf.MessageLiteOrBuilder {

		def hasVarint1: Boolean
		def getVarint1: Int

		def hasVarint2: Boolean
		def getVarint2: Long

		def hasVarint3: Boolean
		def getVarint3: Int

		def hasVarint4: Boolean
		def getVarint4: Long

		def hasVarint5: Boolean
		def getVarint5: Int

		def hasVarint6: Boolean
		def getVarint6: Long

		def hasVarint7: Boolean
		def getVarint7: Boolean

		def has64bit1: Boolean
		def get64bit1: Long

		def has64bit2: Boolean
		def get64bit2: Long

		def has64bit3: Boolean
		def get64bit3: Double

		def hasLengthDelim1: Boolean
		def getLengthDelim1: String

		def hasLengthDelim2: Boolean
		def getLengthDelim2: Array[Byte]

		def hasLengthDelim3: Boolean
		def getLengthDelim3: Embedded

		def hasLengthDelim4: Boolean
		def getLengthDelim4: Int

		def hasLengthDelim5: Boolean
		def getLengthDelim5: Int

		def has32bit1: Boolean
		def get32bit1: Int

		def has32bit2: Boolean
		def get32bit2: Int

		def has32bit3: Boolean
		def get32bit3: Float

	}

	case class DataTypes() extends com.google.protobuf.GeneratedMessageLite with DataTypesOrBuilder {
	}

	object Varint8Enum extends hr.sandrogrzicic.scalabuff.runtime.Enum {
		sealed trait EnumVal extends Value
			
		val ENUM_ZERO = new EnumVal { val name = "ENUM_ZERO"; val id = 0 }
		val ENUM_ONE = new EnumVal { val name = "ENUM_ONE"; val id = 1 }

		val ENUM_ZERO_VALUE = 0
		val ENUM_ONE_VALUE = 1

		def valueOf(id: Int) = id match {
			case 0 => ENUM_ZERO
			case 1 => ENUM_ONE
		}

		val internalGetValueMap = new com.google.protobuf.Internal.EnumLiteMap[EnumVal] {
			def findValueByNumber(id: Int): EnumVal = valueOf(id)
		}
	}


	def registerAllExtensions(registry: com.google.protobuf.ExtensionRegistryLite) {
	}

}