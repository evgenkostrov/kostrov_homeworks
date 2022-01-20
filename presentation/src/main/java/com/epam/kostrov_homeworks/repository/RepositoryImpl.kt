package com.epam.kostrov_homeworks.repository

import com.epam.domain.model.Battery
import com.epam.domain.model.ItemViewModel
import com.epam.domain.repisitory.Repository
import com.epam.kostrov_homeworks.R

class RepositoryImpl():Repository {
    private var beginIndex = 1
    override fun getAllAKB(): List<ItemViewModel> {
        val list = mutableListOf<ItemViewModel>()
        for (a in 0..100 step 3) {
            list.addAll(
                a, listOf(
                    ItemViewModel.Category(
                        CATEGORY[(0..3).shuffled().last()]
                    )
                )
            )

            list.addAll(
                a + 1, listOf(
                    ItemViewModel.Akb(
                        TITLE[(0..9).shuffled().last()],
                        IMAGE[(0..9).shuffled().last()]
                    )
                )
            )
            list.addAll(
                a + 2, listOf(
                    ItemViewModel.Advertisement(
                        "${(10..20).random()}%"
                    )
                )
            )
        }
        return list
    }

    override fun addBattery(): Battery {
        val item=Battery( TITLE[(0..9).shuffled().last()],
                            IMAGE[(0..9).shuffled().last()])

        beginIndex++
        return item
    }

    override fun deleteAdv(item: ItemViewModel): List<ItemViewModel> {
        val list = mutableListOf<ItemViewModel>()
        for (a in 0..100 step 3) {
            list.addAll(
                a, listOf(
                    ItemViewModel.Category(
                        CATEGORY[(0..3).shuffled().last()]
                    )
                )
            )

            list.addAll(
                a + 1, listOf(
                    ItemViewModel.Akb(
                        TITLE[(0..9).shuffled().last()],
                        IMAGE[(0..9).shuffled().last()]
                    )
                )
            )
            list.addAll(
                a + 2, listOf(
                    ItemViewModel.Advertisement(
                        "${(10..20).random()}%"
                    )
                )
            )
        }
        list.remove(item)
        return list
    }

    companion object {
        private val CATEGORY = mutableListOf<String>(
            "STARTER BATTERY",
            "INDUSTRIAL BATTERY",
            "BATTERY FOR WATER TRANSPORT",
            "BATTERY FOR START/STOP SYSTEM"
        )

        private val TITLE = mutableListOf<String>(
            "TUBOR AQUATECH",
            "TUBOR SYNERGY",
            "TUBOR STANDART",
            "TUBOR TRUCK",
            "TUBOR EFB",
            "TUBOR ASIA",
            "TUBOR GEL",
            "TITAN ARCTIC",
            "ARCTIC ASIA",
            "VAIPER"
        )
        private val IMAGE = mutableListOf<Int>(
            R.drawable.t1,
            R.drawable.t2,
            R.drawable.t3,
            R.drawable.t4,
            R.drawable.t5,
            R.drawable.t6,
            R.drawable.t7,
            R.drawable.t8,
            R.drawable.t9,
            R.drawable.t10
        )
    }
}