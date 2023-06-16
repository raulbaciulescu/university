from Tests.test_domain import test_masina
from Tests.test_file_repository import test_file_repository
from Tests.test_masina_service import test_create_masina


def run_all_tests():
    test_masina()
    test_create_masina()
    test_file_repository()

