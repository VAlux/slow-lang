import scala.io.Source
import scala.util.Try

<<<<<<< HEAD
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
=======
@main def hello(args: String*): Unit =
  args.toList match
    case "repl" :: Nil => repl()
    case "file" :: inputFile :: Nil =>
      val filename = inputFile.trim()
      if filename.isEmpty() then printUsage()
      else runFile(filename)
    case _ => printUsage()
>>>>>>> 79f0d5f (arguments parsing, file reading adjustments)

def runFile(filename: String): Unit =
  Try(Source.fromFile(filename).getLines().toList).fold(
    error => println(s"Error reading file $filename because: ${error.getMessage()}"),
    lines =>
      run(lines).fold(
        error => println(s"error occurred on line: ${error.lineNumber}"),
        success => println(success)
      )
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

<<<<<<< HEAD
  Right("Finished!")

def printUsage(): Unit =
  println("""
  Usage: sox [file].sox
=======
=======
>>>>>>> 79f0d5f (arguments parsing, file reading adjustments)
  Right("Bye!")

def printUsage(): Unit =
  println("""
  Usage: slox [file]
>>>>>>> 9221089 (slox lexer POC)
  """)
