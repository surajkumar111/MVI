package com.example.mvi.designPattern

class FactoryProducer {
    fun getFactory(factoryType: String): AbstractFactory {
        return when (factoryType) {
            "LAPTOP" -> {
                LaptopFactory()
            }
            else -> {
                DeskTopFactory()
            }
        }
    }

}


interface Shape {
    fun draw()
}

abstract class AbstractFactory {
    abstract fun getType(shapeType: String): Shape
}


class LaptopFactory : AbstractFactory() {
    override fun getType(shapeType: String): Shape {
        return Laptop()
    }

}

class DeskTopFactory : AbstractFactory() {
    override fun getType(shapeType: String): Shape {
        return DeskTop()
    }

}

class Laptop : Shape {
    override fun draw() {
        TODO("Not yet implemented")
    }

}

class DeskTop : Shape {
    override fun draw() {
        TODO("Not yet implemented")
    }

}
