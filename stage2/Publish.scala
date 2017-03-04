package cbt
import java.io.File
import java.net.URL
import java.nio.file.Files.readAllBytes

trait Publish extends PublishMaven // FIXME: delete
trait PublishMaven extends PackageJars{
  def description: String
  def url: URL
  def developers: Seq[Developer]
  def licenses: Seq[License]
  def scmUrl: String
  def scmConnection: String
  def inceptionYear: Int
  def organization: Option[Organization]

  // ========== publish ==========
  private val releaseFolder = s"/${groupId.replace(".","/")}/${artifactId}_$scalaMajorVersion/$version/"

  def publishedArtifacts = `package` :+ pom

  def publishLocal: Unit = lib.publishLocal(
    sourceFiles, publishedArtifacts, context.paths.mavenCache, releaseFolder
  )
  // ========== package ==========

  /** put additional xml that should go into the POM file in here */
  def pom: File = lib.pom(
    groupId = groupId,
    artifactId = artifactId,
    version = version,
    scalaMajorVersion = scalaMajorVersion,
    name = name,
    description = description,
    url = url,
    developers = developers,
    licenses = licenses,
    scmUrl = scmUrl,
    scmConnection = scmConnection,
    inceptionYear,
    organization,
    dependencies = dependencies,
    jarTarget = jarTarget
  )

}
