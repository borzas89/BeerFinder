package example.com.beerfinder.data.mapper.databaseToPresentation

import example.com.beerfinder.data.api.BaseApiModel
import example.com.beerfinder.data.database.BaseDataModel
import example.com.beerfinder.data.mapper.BaseMapper
import example.com.beerfinder.model.BasePresentationModel

abstract class DataModelToPresentationModelMapper <T : BaseDataModel, R : BasePresentationModel> : BaseMapper<T, R>(){

}