import app.configureKotest
import app.configureKotlin

plugins {
    kotlin("jvm")
    id("loner.verify.datekt")
}

configureKotlin()
configureKotest()