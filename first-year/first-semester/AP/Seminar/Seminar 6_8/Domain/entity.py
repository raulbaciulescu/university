class Entity:
    def __init__(self, id_entity):
        # TODO: needs to be a property
        self.id_entity = id_entity

    def __eq__(self, other):
        return type(self) == type(other) and self.id_entity == other.id_entity