enum TokenType(val lexeme: String | Null = null):
  case L_PAREN extends TokenType("(")
  case R_PAREN extends TokenType(")")
  case L_BRACE extends TokenType("{")
  case R_BRACE extends TokenType("}")
  case COMMA extends TokenType(",")
  case DOT extends TokenType(".")
  case SEMICOLON extends TokenType(";")
  case MINUS extends TokenType("-")
  case PLUS extends TokenType("+")
  case SLASH extends TokenType("/")
  case STAR extends TokenType("*")
  case BANG extends TokenType("!")
  case BANG_EQ extends TokenType("!=")
  case EQ extends TokenType("=")
  case EQ_EQ extends TokenType("==")
  case GT extends TokenType(">")
  case LT extends TokenType("<")
  case GT_EQ extends TokenType(">=")
  case LT_EQ extends TokenType("<=")
  case AND extends TokenType("and")
  case OR extends TokenType("or")
  case IF extends TokenType("if")
  case ELSE extends TokenType("else")
  case TRUE extends TokenType("true")
  case FALSE extends TokenType("false")
  case FOR extends TokenType("for")
  case WHILE extends TokenType("while")
  case PRINT extends TokenType("print")
  case RETURN extends TokenType("return")
  case SUPER extends TokenType("super")
  case THIS extends TokenType("this")
  case VAR extends TokenType("var")
  case NIL extends TokenType("nil")
  case FUN extends TokenType("fun")
  case CLASS extends TokenType("class")
  case IDENTIFIER extends TokenType
  case STRING extends TokenType
  case NUMBER extends TokenType
  case EOF extends TokenType

case class Token(
    tokenType: TokenType,
    literal: Numeric[_] | String | Unit,
    line: Int
)
