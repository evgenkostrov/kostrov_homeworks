package com.epam.data.repository

import com.epam.domain.model.Song
import com.epam.domain.model.SongPreview
import com.epam.domain.repository.MasterSongsRepository

class MasterSongsRepositoryImpl : MasterSongsRepository {
    override fun getSongPreviewList(): List<SongPreview> {
        return songsList.map(Song::getSongPreview)
    }
    companion object{
        private val songsList = listOf(
            Song(
                "0",
                "Low Battery",
                "Zhuki",
                null,
                "1999",
                "Battery",
                "Rock",
                "Cold wind with rain intensified a hundredfold\n" +
                        "Everything says one thing, that there is no way back\n" +
                        "That you are not my burdock, and I am not your Andrey\n" +
                        "That love in our village has a battery"
            ),
            Song(
                "1",
                "Batteries",
                "Fixiki",
                null,
                "2020",
                "TV",
                "For Kids",
                "If something is off\n" +
                        "Do not rush to throw it away or repair it!\n" +
                        "Maybe it's just out of order?\n" +
                        "Maybe the batteries just need to be replaced?"
            ),
            Song(
                "2",
                "Akkumulator",
                "Alica",
                null,
                "1998",
                "The Beach of Siberia on the banks of the Neva",
                "Rock",
                "And I walk in the woods\n" +
                        "And I refuel the battery by the river.\n" +
                        "Akkumulator.\n" +
                        "Akkumulator."
            )
        )
    }
}