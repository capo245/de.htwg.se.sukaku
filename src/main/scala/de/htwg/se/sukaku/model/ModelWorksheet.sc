case class Cell(hints:List[Int], value:Int)
// hints = possible Numbers, value hints displayed in cel bevore filling cannot be empty
// input = the value the user typed in. can be empty

val hints = 1 :: 2 :: 3 :: 4 :: 5 :: 6 :: 7 :: 8 :: 9 :: Nil


val cell1 = Cell(hints, 4)
cell1.hints
cell1.value

case class Field(cells: Array[Cell])

val field1 = Field(Array.ofDim[Cell](1))
field1.cells(0)=cell1
field1.cells(0).hints
field1.cells(0).value

def check_row = { true}
// checks wether all cell.value in a row

def check_all_rows = {true}
// checks wether all field_rows follow sodoku ru

def check_coloumn = {true}


def check_caste = true

// need funktion that controlls wether input is correct
// it should be made out of three funtions: chekc_caste, check_row, check_collumn

val list = 1 :: 2 :: 3 :: Nil