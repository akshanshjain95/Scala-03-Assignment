package knoldus.assignment

import java.io.File



class ConvertToUpperCase extends ReadWriteFromFolder {

  def upperCase(inputPath: String, outputPath: String, extension: String): Boolean = {

    val dir = new File(inputPath)
    val allFileList = getAllFiles(dir)
    val fileList = extension match {
      case "all" => allFileList

      case _ => {
        val fileListCheck = allFileList.filter(f => f.getName.endsWith(extension)).toList

        if(fileListCheck.isEmpty)
          {
            throw new Exception("Either invalid extension or file with such extension doesn't exist in the given path")
          }
        else
          {
            fileListCheck
          }
      }
    }

    val fileContentList = fileList.map(read(_)).map(_.toUpperCase())

    val fileContentTupleList = fileList.zip(fileContentList)

    val checkResult = fileContentTupleList.map(t=>write(t._1,t._2,outputPath))

    if(checkResult.contains(false))
      {
        false
      }
    else
    {
      true
    }
  }

  private def getAllFiles(dir: File): List[File] = {

    val listOfFiles = dir.listFiles().toList

    listOfFiles.filter(_.isFile) ::: listOfFiles.filter(_.isDirectory).flatMap(x => getAllFiles(x))

  }
}
