/**
 * Created by pbartlett on 5/17/16.*/

def sayHi = {
  println 'hi'
}

def recurseChildren = { node ->
  println node
}

sayHi()
recurseChildren(null)

def filename = '/users/pbartlett/desktop/Every One Counts/Nov_2015_EW_Results.xml'
def s = new File(filename).text
def rootNode = new XmlSlurper().parseText(s)
println rootNode
recurseChildren(rootNode)

rootNode.childNodes().each {
//  recurseChildren(it)

}