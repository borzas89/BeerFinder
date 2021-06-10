package example.com.beerfinder.data.mapper.apiToDatabase

import example.com.beerfinder.data.api.BaseApiModel
import example.com.beerfinder.data.database.BaseDataModel
import example.com.beerfinder.data.mapper.BaseMapper


abstract class ApiModelToDataModelMapper<T : BaseApiModel, R : BaseDataModel> : BaseMapper<T, R>(){

}