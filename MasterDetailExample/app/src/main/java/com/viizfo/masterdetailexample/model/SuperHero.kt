package com.viizfo.masterdetailexample.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

data class SuperHero(
    var id:Int,
    var SuperHero:String,
    var publisher:String,
    var realName:String,
    var photo:String,
    var description:String
){
    companion object{
        var listSuper:MutableList<SuperHero> = ArrayList()
        fun getSuperHeroes(): MutableList<SuperHero>{
            listSuper.clear()
            listSuper.add(SuperHero(1,"Spiderman", "Marvel", "Peter Parker", "spiderman.jpg",LoremIpsum().values.first()))
            listSuper.add(SuperHero(2,"Daredevil", "Marvel", "Matthew Michael Murdock", "daredevil.jpg",LoremIpsum().values.first()))
            listSuper.add(SuperHero(3,"Wolverine", "Marvel", "James Howlett", "logan.jpeg",LoremIpsum().values.first()))
            listSuper.add(SuperHero(4,"Batman", "DC", "Bruce Wayne", "batman.jpg",LoremIpsum().values.first()))
            listSuper.add(SuperHero(5,"Thor", "Marvel", "Thor Odinson", "thor.jpg",LoremIpsum().values.first()))
            listSuper.add(SuperHero(6,"Flash", "DC", "Jay Garrick", "flash.png",LoremIpsum().values.first()))
            listSuper.add(SuperHero(7,"Superman", "DC", "Clark Kent", "superman.jpg",LoremIpsum().values.first()))
            listSuper.add(SuperHero(8,"Iron Man", "DC", "Tonny Stark", "ironman.jpg",LoremIpsum().values.first()))
            listSuper.add(SuperHero(9,"Green Lantern", "DC", "Alan Scott", "green_lantern.jpg",LoremIpsum().values.first()))
            listSuper.add(SuperHero(10,"Wonder Woman", "DC", "Princess Diana", "wonder_woman.jpg",LoremIpsum().values.first()))
            return listSuper
        }
        fun getSuperHeroById(mId: Int?):SuperHero?{
            val superhero = getSuperHeroes().filter { superHero ->
                superHero.id==mId
            }

            return  superhero[0]?:null
        }

        fun getFirstID():Int{
            return getSuperHeroes()[0].id
        }
    }
}