package knoldus.assignment

import java.io.File


trait ReadWrite {

  def read(f:File): String

  def write(file: File, content: String, dirPath: String): Boolean
}
