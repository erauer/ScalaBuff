// Generated by ScalaBuff, the Scala protocol buffer compiler. DO NOT EDIT!
// source: simpleWithComments.proto

object SimpleWithComments {
	final class SimpleRequest private (
		private var _query: String = "", 
		private var _pageNumber: Int = 0, 
		private var _resultsPerPage: Int = 0, 
		private var setFields: collection.BitSet = collection.BitSet.empty
	) extends com.google.protobuf.GeneratedMessageLite
		with com.google.protobuf.MessageLiteOrBuilder
		with hr.sandrogrzicic.scalabuff.runtime.Message[SimpleRequest] {
		def hasQuery = setFields.contains(0)
		def hasPageNumber = setFields.contains(0)
		def hasResultsPerPage = setFields.contains(0)
	}

	object SimpleRequest {
		def apply() = defaultInstance
		def apply(message: SimpleRequest = defaultInstance.mergeFrom(message)
		def apply(
				query: String = "",
				pageNumber: Option[Int] = None,
				resultsPerPage: Option[Int] = None
		) = {
			val setFields = collection.mutable.BitSet.empty
			new SimpleRequest(
				query,
				pageNumber.getOrElse(0),
				resultsPerPage.getOrElse(0)
			)
		}
		val defaultInstance = new SimpleRequest()
		def getDefaultInstance = defaultInstance

		val QUERY_FIELD_NUMBER = 1
		val PAGE_NUMBER_FIELD_NUMBER = 2
		val RESULTS_PER_PAGE_FIELD_NUMBER = 3

	}

	def registerAllExtensions(registry: com.google.protobuf.ExtensionRegistryLite) {
	}

}