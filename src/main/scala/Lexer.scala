<<<<<<< HEAD
object Lexer:

  sealed trait LexerError(reason: String, line: Int, column: Int)
  case class UnknownLexeme(lexeme: String, line: Int, column: Int) extends LexerError(s"Unknown lexeme: $lexeme", line, column)
=======
import scala.annotation.tailrec
import TokenType.*

object Lexer:

  sealed trait LexerError(reason: String, line: Int, column: Int)
  case class UnknownLexeme(lexeme: String, line: Int, column: Int)
      extends LexerError(s"Unknown lexeme: $lexeme", line, column)
  case class UnterminatedStringLexeme(line: Int, column: Int) extends LexerError("Unterminated string", line, column)
>>>>>>> 9221089 (slox lexer POC)

  val lexemeTokenTypeMapping: Map[String, TokenType] =
    TokenType.values
      .filter(_.lexeme != null)
      .map(value => value.lexeme -> value)
      .toMap

  def parseTokens(lines: List[String]): List[Token | LexerError] =
    lines.zipWithIndex.flatMap(parseLine)

  def parseLine(line: String, lineNumber: Int): List[Token | LexerError] =
<<<<<<< HEAD
    line
      .flatMap(character => lexemeTokenTypeMapping.get(character.toString()))
      .map(lexemeType => LexemeToken(lexemeType, lexemeType.lexeme, lineNumber))
      .toList

=======
    val whiteSpaces = Set(' ', '\t', '\r')

    def parseStringLiteral(content: List[Char], line: Int, column: Int): (List[Char], Token | LexerError, Int) =
      if content.contains('"') then
        val (literal, rem) = content.splitAt(content.indexOf('"'))
        (rem.tail, Token(STRING, literal.mkString, line), literal.length)
      else (List.empty, UnterminatedStringLexeme(line, column), content.length)

    def parseNumberLiteral(content: List[Char], line: Int, column: Int): (List[Char], Token | LexerError, Int) =
      ???

    @tailrec
    def parseTokens(
        current: List[Char],
        parsed: List[Token | LexerError] = List.empty,
        index: Int = 0
    ): List[Token | LexerError] =
      if current.isEmpty then parsed
      else
        val (tail, token, length) = current match
          case ('\t' | '\r' | ' ') :: tail                    => (tail, null, 1)
          case 'a' :: 'n' :: 'd' :: tail                      => (tail, Token(AND, null, lineNumber), 3)
          case 'o' :: 'r' :: tail                             => (tail, Token(OR, null, lineNumber), 2)
          case 'i' :: 'f' :: tail                             => (tail, Token(IF, null, lineNumber), 2)
          case 'e' :: 'l' :: 's' :: 'e' :: tail               => (tail, Token(ELSE, null, lineNumber), 4)
          case 't' :: 'r' :: 'u' :: 'e' :: tail               => (tail, Token(TRUE, null, lineNumber), 4)
          case 'f' :: 'a' :: 'l' :: 's' :: 'e' :: tail        => (tail, Token(FALSE, null, lineNumber), 5)
          case 'f' :: 'o' :: 'r' :: tail                      => (tail, Token(FOR, null, lineNumber), 3)
          case 'w' :: 'h' :: 'i' :: 'l' :: 'e' :: tail        => (tail, Token(WHILE, null, lineNumber), 5)
          case 'p' :: 'r' :: 'i' :: 'n' :: 't' :: tail        => (tail, Token(PRINT, null, lineNumber), 5)
          case 'r' :: 'e' :: 't' :: 'u' :: 'r' :: 'n' :: tail => (tail, Token(RETURN, null, lineNumber), 6)
          case 's' :: 'u' :: 'p' :: 'e' :: 'r' :: tail        => (tail, Token(SUPER, null, lineNumber), 5)
          case 't' :: 'h' :: 'i' :: 's' :: tail               => (tail, Token(THIS, null, lineNumber), 4)
          case 'v' :: 'a' :: 'r' :: tail                      => (tail, Token(VAR, null, lineNumber), 3)
          case 'n' :: 'i' :: 'l' :: tail                      => (tail, Token(NIL, null, lineNumber), 3)
          case 'f' :: 'u' :: 'n' :: tail                      => (tail, Token(FUN, null, lineNumber), 3)
          case 'c' :: 'l' :: 'a' :: 's' :: 's' :: tail        => (tail, Token(CLASS, null, lineNumber), 5)
          case '!' :: '=' :: tail                             => (tail, Token(BANG_EQ, null, lineNumber), 2)
          case '=' :: '=' :: tail                             => (tail, Token(EQ_EQ, null, lineNumber), 2)
          case '<' :: '=' :: tail                             => (tail, Token(LT_EQ, null, lineNumber), 2)
          case '>' :: '=' :: tail                             => (tail, Token(GT_EQ, null, lineNumber), 2)
          case '>' :: tail                                    => (tail, Token(GT, null, lineNumber), 1)
          case '<' :: tail                                    => (tail, Token(LT, null, lineNumber), 1)
          case '(' :: tail                                    => (tail, Token(L_PAREN, null, lineNumber), 1)
          case ')' :: tail                                    => (tail, Token(R_PAREN, null, lineNumber), 1)
          case '{' :: tail                                    => (tail, Token(L_BRACE, null, lineNumber), 1)
          case '}' :: tail                                    => (tail, Token(R_BRACE, null, lineNumber), 1)
          case ',' :: tail                                    => (tail, Token(COMMA, null, lineNumber), 1)
          case '.' :: tail                                    => (tail, Token(DOT, null, lineNumber), 1)
          case ';' :: tail                                    => (tail, Token(SEMICOLON, null, lineNumber), 1)
          case '-' :: tail                                    => (tail, Token(MINUS, null, lineNumber), 1)
          case '+' :: tail                                    => (tail, Token(PLUS, null, lineNumber), 1)
          case '/' :: tail                                    => (tail, Token(SLASH, null, lineNumber), 1)
          case '*' :: tail                                    => (tail, Token(STAR, null, lineNumber), 1)
          case '!' :: tail                                    => (tail, Token(BANG, null, lineNumber), 1)
          case '=' :: tail                                    => (tail, Token(EQ, null, lineNumber), 1)
          case '"' :: tail                                    => parseStringLiteral(tail, lineNumber, index)
          case ch :: tail if ch > 0 && ch < 9                 => parseNumberLiteral(tail, lineNumber, index)
          case lexemes => (lexemes.tail, UnknownLexeme(current.head.toString, lineNumber, index + 1), 1)

        if token != null then parseTokens(tail, parsed :+ token, index + length)
        else parseTokens(tail, parsed, index + length)

    parseTokens(line.toList)
>>>>>>> 9221089 (slox lexer POC)
