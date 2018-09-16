package exercise2

object CompositionsSolution {

  def compose[A, B, C, D](f: A => B)
                         (g: B => C)
                         (h: C => D): A => D = h.compose(g.compose(f))

  def mapFlatMap[A, B, C, D](f: A => Option[B])
                            (g: B => Option[C])
                            (h: C => D): Option[A] => Option[D] = opt => {
    opt
      .flatMap(f)
      .flatMap(g)
      .map(h)
  }

  def forComprehension[A, B, C, D](f: A => Option[B])
                                  (g: B => Option[C])
                                  (h: C => D): Option[A] => Option[D] = opt => {
    for {
        v <- opt
        d <- f(v)
        s <- g(d)
      } yield h(s)
  }
}
