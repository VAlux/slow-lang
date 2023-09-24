import scala.io.Source
import scala.util.Try

@main def entrypoint(args: String*): Unit =
  args match
    case Nil => repl()
    case (filename: String) :: Nil => runFile(filename)
    case _ => printUsage()

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
    print("sox :> ")
    val line = scala.io.StdIn.readLine()
    if line.isBlank() then return
    else run(List(line))

case class Error(lineNumber: Int)

def run(lines: List[String]): Either[Error, String] =
  val tokens = Lexer.parseTokens(lines)
  // running
  tokens.foreach(println)
  //

  Right("Finished!")

def printUsage(): Unit =
  println("""
  Usage: sox [file].sox
  """)
