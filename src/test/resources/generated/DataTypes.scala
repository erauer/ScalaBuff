// Generated by ScalaBuff, the Scala protocol buffer compiler. DO NOT EDIT!
// source: dataTypes.proto

object DataTypes {
	final class DataTypes private (
		private var _varint1: Int = 0, 
		private var _varint2: Long = 0L, 
		private var _varint3: Int = 0, 
		private var _varint4: Long = 0L, 
		private var _varint5: Int = 0, 
		private var _varint6: Long = 0L, 
		private var _varint7: Boolean = false, 
		private var _f64bit1: Long = 0L, 
		private var _f64bit2: Long = 0L, 
		private var _f64bit3: Double = 0.0, 
		private var _lengthDelim1: String = "", 
		private var _lengthDelim2: com.google.protobuf.ByteString = com.google.protobuf.ByteString.EMPTY, 
		private var _lengthDelim3: Varint8Enum = null, 
		private var _lengthDelim4: Vector[Int] = Vector.empty[Int], 
		private var _lengthDelim5: Vector[Int] = Vector.empty[Int], 
		private var _f32bit1: Int = 0, 
		private var _f32bit2: Int = 0, 
		private var _f32bit3: Float = 0.0f, 
		private var setFields: collection.BitSet = collection.BitSet.empty
	) extends com.google.protobuf.GeneratedMessageLite
		with com.google.protobuf.MessageLiteOrBuilder
		with hr.sandrogrzicic.scalabuff.runtime.Message[DataTypes] {
		def hasVarint1 = setFields.contains(0)
		def hasVarint2 = setFields.contains(0)
		def hasVarint3 = setFields.contains(0)
		def hasVarint4 = setFields.contains(0)
		def hasVarint5 = setFields.contains(0)
		def hasVarint6 = setFields.contains(0)
		def hasVarint7 = setFields.contains(0)
		def hasF64bit1 = setFields.contains(0)
		def hasF64bit2 = setFields.contains(0)
		def hasF64bit3 = setFields.contains(0)
		def hasLengthDelim1 = setFields.contains(0)
		def hasLengthDelim2 = setFields.contains(0)
		def hasLengthDelim3 = setFields.contains(0)
		def hasF32bit1 = setFields.contains(0)
		def hasF32bit2 = setFields.contains(0)
		def hasF32bit3 = setFields.contains(0)
	}

	object DataTypes {
		def apply() = defaultInstance
		def apply(message: DataTypes = defaultInstance.mergeFrom(message)
		def apply(
				varint1: Int = 0,
				varint2: Option[Long] = None,
				varint3: Option[Int] = None,
				varint4: Long = 0L,
				varint5: Option[Int] = None,
				varint6: Option[Long] = None,
				varint7: Option[Boolean] = None,
				f64bit1: Option[Long] = None,
				f64bit2: Option[Long] = None,
				f64bit3: Option[Double] = None,
				lengthDelim1: Option[String] = None,
				lengthDelim2: Option[com.google.protobuf.ByteString] = None,
				lengthDelim3: Option[Varint8Enum] = None,
				lengthDelim4: Vector[Int] = Vector.empty[Int],
				lengthDelim5: Vector[Int] = Vector.empty[Int],
				f32bit1: Option[Int] = None,
				f32bit2: Option[Int] = None,
				f32bit3: Option[Float] = None
		) = {
			val setFields = collection.mutable.BitSet.empty
			new DataTypes(
				varint1,
				varint2.getOrElse(0L),
				varint3.getOrElse(0),
				varint4,
				varint5.getOrElse(0),
				varint6.getOrElse(0L),
				varint7.getOrElse(false),
				f64bit1.getOrElse(0L),
				f64bit2.getOrElse(0L),
				f64bit3.getOrElse(0.0),
				lengthDelim1.getOrElse(""),
				lengthDelim2.getOrElse(com.google.protobuf.ByteString.EMPTY),
				lengthDelim3.getOrElse(null),
				lengthDelim4,
				lengthDelim5,
				f32bit1.getOrElse(0),
				f32bit2.getOrElse(0),
				f32bit3.getOrElse(0.0f)
			)
		}
		val defaultInstance = new DataTypes()
		def getDefaultInstance = defaultInstance

		val VARINT1_FIELD_NUMBER = 1
		val VARINT2_FIELD_NUMBER = 2
		val VARINT3_FIELD_NUMBER = 3
		val VARINT4_FIELD_NUMBER = 4
		val VARINT5_FIELD_NUMBER = 5
		val VARINT6_FIELD_NUMBER = 6
		val VARINT7_FIELD_NUMBER = 7
		val F64BIT1_FIELD_NUMBER = 100
		val F64BIT2_FIELD_NUMBER = 101
		val F64BIT3_FIELD_NUMBER = 102
		val LENGTH_DELIM1_FIELD_NUMBER = 200
		val LENGTH_DELIM2_FIELD_NUMBER = 201
		val LENGTH_DELIM3_FIELD_NUMBER = 202
		val LENGTH_DELIM4_FIELD_NUMBER = 204
		val LENGTH_DELIM5_FIELD_NUMBER = 203
		val F32BIT1_FIELD_NUMBER = 500
		val F32BIT2_FIELD_NUMBER = 501
		val F32BIT3_FIELD_NUMBER = 502

		object Varint8Enum extends hr.sandrogrzicic.scalabuff.runtime.Enum {
			sealed trait EnumVal extends Value
				
			val ENUM_ZERO = new EnumVal { val name = "ENUM_ZERO"; val id = 0 }
			val ENUM_ONE = new EnumVal { val name = "ENUM_ONE"; val id = 1 }

			val ENUM_ZERO_VALUE = 0
			val ENUM_ONE_VALUE = 1

			def valueOf(id: Int) = (id: @annotation.switch) match {
				case 0 => ENUM_ZERO
				case 1 => ENUM_ONE
			}
			val internalGetValueMap = new com.google.protobuf.Internal.EnumLiteMap[EnumVal] {
				def findValueByNumber(id: Int): EnumVal = valueOf(id)
			}
		}

	}

	def registerAllExtensions(registry: com.google.protobuf.ExtensionRegistryLite) {
	}

}