class UndoRedoService:

    def __init__(self):
        self.__undo_stack = []

    def add_to_undo(self, operation):
        self.__undo_stack.append(operation)

    def do_undo(self):
        self.__undo_stack.pop().undo()