import openai
import time

openai.api_key = "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"
prompt = "cine esti?"
model = "text-davinci-003"

response = openai.Completion.create(
  model=model,
  prompt="Scrie un program simplu in C++",
  max_tokens=1000,
  temperature=0.1,
  n=1
)

print(response.choices[0].text)