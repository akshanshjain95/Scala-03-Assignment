package knoldus.assignment
import java.io.{File, PrintWriter}

import scala.io.Source.fromFile



class ReadWriteFromFolder extends ReadWrite{

  override def read(f: File): String = {

    fromFile(f).getLines.mkString("\n")

  }

  override def write(file: File, content: String, dirPath: String): Boolean = {

    new File(dirPath).mkdir()
    val writeToFile = new PrintWriter(dirPath + "/" + file.getName)
    try {

      writeToFile.write(content)
      true

    }
    catch{

      case e: Exception => false

    }
    finally {

      writeToFile.close()

    }
  }

}
