package com.apolis.propertymanagement.data.models

data class ImageApi(
    val acl: String,
    val bucket: String,
    val contentDisposition: Any,
    val contentType: String,
    val encoding: String,
    val etag: String,
    val fieldname: String,
    val key: String,
    val location: String,
    val metadata: Metadata,
    val mimetype: String,
    val originalname: String,
    val serverSideEncryption: Any,
    val size: Int,
    val storageClass: String
)