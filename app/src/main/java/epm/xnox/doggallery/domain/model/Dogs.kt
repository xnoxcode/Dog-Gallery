package epm.xnox.doggallery.domain.model

data class Dogs(
    val status: String?,
    val images: List<String>
)

fun DogResponse.toDomain() = Dogs(
    status = status,
    images = images
)

