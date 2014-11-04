#
# typeOperation(value)
#

# normal
0x1 :: 0x1

# ...with newlines
0x1
::
0x1

# right associative
# should parse as 0o3 :: (0x3 ::0b11)
0o3 :: 0x3 :: 0b11

# typeOperation(atOperation(value))
# should parse as (@0x1) :: (@0x2)
@0x1 :: @0x2

# typeOperation(unaryOperation(value))
# should parse as 0x1 :: (~~~0x1)
0x1 :: ~~~0x1

# typeOperation(hatOperation(value))
# should parse as (0x1 ^^^ 0x2) :: (0x2 ^^^ 0x1)
0x1 ^^^ 0x2 :: 0x2 ^^^ 0x1

# typeOperation(multiplicationOperation(value))
# should parse as (0x1 * 0x2) :: (0x2 * 0x1)
0x1 * 0x2 :: 0x2 * 0x1

# typeOperation(additionOperation(value))
# should parse as 0x3 :: (0x1 + 0x2)
0x3 :: 0x1 + 0x2

# typeOperation(twoOperation(value))
# should parse as (0x1..0x2) :: (0x3..0x4)
0x1..0x2 :: 0x3..0x4

# typeOperation(arrowOperation(value))
# should parse as ('a' |> 'b') :: ('c' |> 'd')
'a' |> 'b' :: 'c' |> 'd'

# typeOperation(relationalOperation(value))
# should parse as (0x1 < 0x2) :: (0x2 > 0x1)
0x1 < 0x2 :: 0x2 > 0x1

# typeOperation(comparisonOperation(value))
# should parse as (0x1 != 0x2) :: (0x1 == 0x1)
0x1 != 0x2 :: 0x1 == 0x1

# typeOperation(andOperation(value))
# should parse as (0x1 &&& 0x2) :: (0x2 &&& 0x1)
0x1 &&& 0x2 :: 0x2 &&& 0x1

# typeOperation(orOperation(value))
# should parse as (0x1 ||| 0x2) :: (0x2 ||| 0x1)
0x1 ||| 0x2 :: 0x2 ||| 0x1

# typeOperation(matchOperation(value))
# should parse as (0x1 = 0x1) :: (0x2 = 0x2)
0x1 = 0x1 :: 0x2 = 0x2

# typeOperation(associationOperation(value))
# should parse as (0x1 => 0x2) :: (0x3 => 0x4)
0x1 => 0x2 :: 0x3 => 0x4

# typeOperation(pipeOperation(value))
# should parse as (0x1 | 0x2) :: (0x3 | 0x4)
0x1 | 0x2 :: 0x3 | 0x4
