object Lexer:

  sealed trait LexerError(reason: String, line: Int, column: Int)
  case class UnknownLexeme(lexeme: String, line: Int, column: Int) extends LexerError(s"Unknown lexeme: $lexeme", line, column)

  val lexemeTokenTypeMapping: Map[String, TokenType] =
    TokenType.values
      .filter(_.lexeme != null)
      .map(value => value.lexeme -> value)
      .toMap

  def parseTokens(lines: List[String]): List[Token | LexerError] =
    lines.zipWithIndex.flatMap(parseLine)

  def parseLine(line: String, lineNumber: Int): List[Token | LexerError] =
    line
      .flatMap(character => lexemeTokenTypeMapping.get(character.toString()))
      .map(lexemeType => LexemeToken(lexemeType, lexemeType.lexeme, lineNumber))
      .toList

