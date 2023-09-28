package org.style.coding

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class LinuxKernelCodingStylePluginSuite extends AnyWordSpec with Matchers {

  val stylePlugin: LinuxKernelCodingStylePlugin = LinuxKernelCodingStylePlugin()

  "LinuxKernelCodingStylePlugin" should {
    "success transform" in {
      val actual = stylePlugin.transform("long_word1 word2 word3 word4", 11)
      actual shouldBe
        s"""long_word1<br>
           |word2 word3<br>
           |word4<br>"""
          .stripMargin
    }

    "skip transform text less than limit to one line" in {
      val actual = stylePlugin.transform("long_word1 word2 word3 word4", 40)
      actual shouldBe
        s"""long_word1 word2 word3 word4"""
          .stripMargin
    }

    "transform with a character limit per line less than words" in {
      val actual = stylePlugin.transform("word1 word2", 3)
      actual shouldBe
        s"""word1<br>
           |word2<br>"""
          .stripMargin
    }

    "transform long text" in {
      val actual = stylePlugin.transform("In 1991, while studying computer science at University of Helsinki, Linus Torvalds began a project that later became the Linux kernel. He wrote the program specifically for the hardware he was using and independent of an operating system because he wanted to use the functions of his new PC with an 80386 processor. Development was done on MINIX using the GNU C Compiler.", 40)
      actual shouldBe
        s"""In 1991, while studying computer science<br>
           |at University of Helsinki, Linus<br>
           |Torvalds began a project that later<br>
           |became the Linux kernel. He wrote the<br>
           |program specifically for the hardware he<br>
           |was using and independent of an<br>
           |operating system because he wanted to<br>
           |use the functions of his new PC with an<br>
           |80386 processor. Development was done on<br>
           |MINIX using the GNU C Compiler.<br>"""
          .stripMargin
    }
  }

}
