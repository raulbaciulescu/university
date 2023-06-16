from Domain.vot import Vot
from Domain.vot_validator import VotValidator
from Repository.vot_repository import VotRepository


class VotService:
    def __init__(self, vot_repository: VotRepository, vot_validator: VotValidator):
        self.__vot_repository = vot_repository
        self.__vot_validator = vot_validator

    def add_vot(self, loc_alba, loc_neagra):
        vote = Vot(loc_alba, loc_neagra)
        self.__vot_validator.validate(vote)
        self.__vot_repository.upsert(vote)

    def update_vot(self, id_vot, loc_alba, loc_neagra):
        pass
        # current_vote = self.__vot_repository.read(id_vot)
        # if current_vote is not None:
        #      current_vote.loc_alba = loc_alba
        #
        #
        #     self.__vot_repository.upsert(ne)


    def get_all(self):
        return self.__vot_repository.read()
