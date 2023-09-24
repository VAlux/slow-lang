import scala.io.Source

<<<<<<< HEAD
@main def entrypoint(args: String*): Unit =
  args match
    case Nil => repl()
    case (filename: String) :: Nil => runFile(filename)
    case _ => printUsage()
=======
@main def hello(filename: String): Unit =
  repl()
  // if filename.isBlank() then printUsage()
  // else runFile(filename)
>>>>>>> 9221089 (slox lexer POC)

def runFile(filename: String): Unit =
  val lines = Source.fromFile(filename).getLines().toList
  run(lines)
    .fold(
      error => println(s"error occurred on line: ${error.lineNumber}"),
      success => println(success)
    )

def repl(): Unit =
  while (true)
<<<<<<< HEAD
    print("sox :> ")
    val line = scala.io.StdIn.readLine()
    if line.isBlank() then return
=======
    print("slox :> ")
    val line = scala.io.StdIn.readLine()
    if line.isEmpty() then return
>>>>>>> 9221089 (slox lexer POC)
    else run(List(line))

case class Error(lineNumber: Int)

def run(lines: List[String]): Either[Error, String] =
  val tokens = Lexer.parseTokens(lines)
  // running
  tokens.foreach(println)
  //

<<<<<<< HEAD
  Right("Finished!")

def printUsage(): Unit =
  println("""
  Usage: sox [file].sox
=======
  Right("Bye!")

def printUsage(): Unit =
  println("""
  Usage: slox [file]
>>>>>>> 9221089 (slox lexer POC)
  """)
