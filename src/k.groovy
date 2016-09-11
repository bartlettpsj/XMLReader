def xml = "https://nexus.e1c.net/service/local/lucene/search?g=com.everyonecounts.modules&a=common&count=9999".toURL().text
def root = new XmlParser().parseText(xml)

def allVersions = []
root.data.artifact.each {
  def name = it.version.text().minus("-SNAPSHOT")
  allVersions.add(name)
}

allVersions.unique().sort()

println allVersions

def blessedVersions = []
allVersions.each {
  if (it.startsWith('blessed')) {
    blessedVersions.add(it)
  }
}

def finalVersions = []
allVersions.each {
  def version = it
  if (it.startsWith('blessed')) {
    finalVersions.add(it)
  }
  else {
    def keepVersion = true
    blessedVersions.each {
      def parts = version.split('-')
      parts[0] = 'blessed'
      if(parts.join('-') == it) {
        keepVersion = false
      }
    }
    if (keepVersion) {
      finalVersions.add(version)
    }
  }
}

finalVersions.join(',')