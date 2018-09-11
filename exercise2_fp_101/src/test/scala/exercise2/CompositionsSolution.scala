package exercise2

object CompositionsSolution {

  def map[A, B, C](e: Either[A, B])(f: B => C): Either[A, C] = e match {
    case Right(r) => Right(f(r))
    case Left(l)  => Left(l)
  }

  def flatMap[A, B, C](e: Either[A, B])(f: B => Either[A, C]): Either[A, C] = e match {
    case Right(r) => f(r)
    case Left(l)  => Left(l)
  }

  def forComprehension[A, B, C, D, E](e: Either[A, B]) 
                                     (f: B => Either[A, C])
                                     (g: C => Either[A, D])
                                     (h: D => E): Either[A, E] = 
    for {
        v <- e
        d <- f(v)
        s <- g(d)
      } yield h(s)
}
