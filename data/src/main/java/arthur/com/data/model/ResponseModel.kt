package arthur.com.data.model

interface ResponseModel<out T: DataModel> {
    val status: String
    val data : List<T>
}