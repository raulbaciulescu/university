from Domain.vot_validator import VotValidator
from Repository.vot_repository import VotRepository
from Service.vot_service import VotService
from UserInterface.console import Console


def main():
    vot_validator = VotValidator()
    vot_repository = VotRepository()

    vot_service = VotService(vot_repository, vot_validator)

    console = Console(vot_service)
    console.run_console()
main()