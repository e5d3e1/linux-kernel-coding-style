package org.style.coding

class LinuxKernelCodingStylePlugin {

  def transform(text: String, limit: Int): String = if (text.length <= limit)
    text
  else
    transform(text.split("\\s+"), limit)

  private def transform(words: Array[String], limit: Int): String = words
    .foldLeft(Seq[String]())((result, word) => result match {
      case Nil =>
        checkMaxWordSize(limit, word)
        Seq(word)
      case xs :+ x =>
        val maybeNextLine = s"$x $word"
        if (maybeNextLine.length <= limit) {
          xs :+ maybeNextLine
        } else {
          checkMaxWordSize(limit, word)
          xs :+ x :+ word
        }
    })
    .mkString("", "<br>\n", "<br>")

  private def checkMaxWordSize(limit: Int, word: String): Unit = {
    if (word.length > limit) {
      println(s"Warning word $word is longest $limit")
    }
  }
}

object LinuxKernelCodingStylePlugin {

  def apply() = new LinuxKernelCodingStylePlugin()
}
