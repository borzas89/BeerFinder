package example.com.beerfinder.data.mapper.databaseToPresentation


import example.com.beerfinder.data.database.MarkerDataModel
import example.com.beerfinder.model.MarkerPresentationModel
import javax.inject.Inject

class MarkerDataModelToPresentationModelMapper @Inject constructor() :
    DataModelToPresentationModelMapper<MarkerDataModel, MarkerPresentationModel>() {

    override fun map(model: MarkerDataModel): MarkerPresentationModel =
        MarkerPresentationModel(
            id = model.id,
            name = model.name,
            city = model.city,
            address = model.address,
            urlToImage = model.urlToImage!!,
            description = model.description!!,
            latitude = model.latitude,
            longitude = model.longitude
        )
}
