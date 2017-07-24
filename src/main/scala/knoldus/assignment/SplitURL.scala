package knoldus.assignment

import java.io.File

object  ExtractHost {

  def unapply(urlWithoutProtocol: String): Option[(String,String,Option[Map[String,String]])] = {

    val hostDomainPart = urlWithoutProtocol.split("/")
    val hostDomain = hostDomainPart(0).split("\\.")
    if(hostDomain.length == 1) {
      None
    }
    else
      {
        val domain = hostDomain.reverse.takeWhile(_ != hostDomain(1)).reverse.mkString(".")

        val queryParamsMap = if(hostDomainPart.length > 1)
        {
          if(hostDomainPart.last.contains("&"))
          {
            Some(hostDomainPart.last.split("\\?").toList.last.split("&").toList
              .map(_.split("=")).map(s => (s(0), s(1))).toMap)
          }
          else
          {
            None
          }
        }
        else
        {
          None
        }
        Some(hostDomain(1), domain,queryParamsMap)
      }
}
}

object ExtractProtocol {

  def unapply(url: String): Option[(String,String)]/*Option[(String, String, String, Option[Map[String,String]])]*/ = {

    val protocolPart = url.split("://").toList
    if(protocolPart.length == 1)  None else Some(protocolPart(0), protocolPart(1))
  }
}

class SplitURL extends ReadWriteFromFolder {

  def splitFile(filePath: String, outputPath: String): Boolean = {
    val file = new File(filePath)
    val fileContent = read(file)
    val fileContentList = fileContent.split("\n").toList

    val splitResult = fileContentList.map{
      _ match {
        case ExtractProtocol(protocol,ExtractHost(host,domain,None)) => "Protocol -> " + protocol + "\n" +
          "Host -> " + host + "\n" +
          "Domain -> " + domain + "\n\n"

        case ExtractProtocol(protocol,ExtractHost(host,domain,Some(value))) => "Protocol -> " + protocol + "\n" +
          "Host -> " + host + "\n" +
          "Domain -> " + domain + "\n" +
          "Query Params -> " + value + "\n\n"

        case _ => "Invalid URL Address\n\n"
      }
    }

    write(file, splitResult.mkString, outputPath)
  }

}
