package dao.cacao.dnd5sheet.data.storage.local.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dao.cacao.dnd5sheet.domain.model.Ability

object Converters {

    //Ability
    @TypeConverter fun toAbility(ability: Ability): String = Gson().toJson(ability)
    @TypeConverter fun fromAbility(json: String): Ability = Gson().fromJson(json)

    //Ability List
    @TypeConverter fun toAbilityList(list: List<Ability>): String = Gson().toJson(list)
    @TypeConverter fun fromAbilityList(json: String): List<Ability> = Gson().fromJson(json)

    private inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object : TypeToken<T>() {}.type)
}