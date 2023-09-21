package org.style.coding

class LinuxKernelCodingStylePlugin {

  def transform(text: String, limit: Int): String = if (text.length <= limit) {
    text
  } else {
    text.split("\\s+").foldLeft(Seq[String]())((result, word) => result match {
        case Nil =>
          if (word.length > limit) {
            println(s"Warning word $word is longest $limit")
          }
          Seq(word)
        case x :: xs =>
          val maybeNextLine = s"$x $word"
          if (maybeNextLine.length <= limit) {
            maybeNextLine +: xs
          } else {
            if (word.length > limit) {
              println(s"Warning word $word is longest $limit")
            }
            word +: x +: xs
          }
      })
      .reverse
      .mkString("", "<br>\n", "<br>")
  }
}

object LinuxKernelCodingStylePlugin {

  def apply() = new LinuxKernelCodingStylePlugin()
}
