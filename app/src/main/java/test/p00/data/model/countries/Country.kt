package test.p00.data.model.countries

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Peter Bukhal on 5/14/18.
 */
@Entity(tableName = "countries")
data class Country(
        @PrimaryKey
        var code: Int = 0,
        var iso2: String = "",
        var iso3: String = "",
        var name: String = "",
        @Ignore
        var flag: String = "",
        @ColumnInfo(name = "calling_code")
        var callingCode: Int = 0)