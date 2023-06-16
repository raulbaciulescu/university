; Compile:
; nasm -f win32 iodemo.asm
; nlink iodemo.obj -lio -o iodemo.exe
%include 'io.inc'
global main
section .text
main:
	call io_readint
	mov [a], eax
	call io_readint
	mov [b], eax
	mov eax, 0
	add eax, [a]
	sub eax, [b]
	mov dword [c], eax
	mov eax, [c]
	call io_writeint
	mov eax, 0
	add eax, [a]
	add eax, [b]
	mov dword [c], eax
	mov eax, [c]
	call io_writeint
	mov eax, 0
	add eax, [a]
	mov ebx, [b]
	imul ebx
	mov dword [c], eax
	mov eax, [c]
	call io_writeint
	mov eax, 0
	add eax, [a]
	mov edx, 0
	mov ebx, [b]
	idiv ebx
	mov dword [c], eax
	mov eax, [c]
	call io_writeint
	ret


section .data
	a dd 0
	b dd 0
	c dd 0


