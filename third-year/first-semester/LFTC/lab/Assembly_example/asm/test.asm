%include 'io.inc'
global main
section .text
main:
	call io_readint
	mov a, eax
	call io_readint
	mov b, eax
	mov eax, 0
	add eax, [a]
	add eax, [b]
	mov dword [c], eax
	mov eax, c
	call io_writeint
section .data
	a dd 0
	b dd 0
	c dd 0