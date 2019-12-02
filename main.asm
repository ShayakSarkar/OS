[org 0x7c00]	; this is important because the boot sector code originates 		    
		; from this address. Therefore if this is not specified
		; then the bytes we define will be defined elsewhere in the
		; memory. 

call _print
jmp $

_print:			;print whatever is in the buffer
	mov bx,buffer
	mov cl,0	
	P_loop_label1:	
		cmp byte [bx],0x00
		je P_break
		mov ah,0x0e
		mov al,byte [bx]
		int 0x10
		inc bx
		inc cl
		jmp P_loop_label1
	P_break:
	
	mov ah,0x0e
	add cl,48
	mov al,cl
	ret

buffer db "Shayak is awesome",0x00	; it is important to define addition					    
					; al bytes later on because the bios
					; will try to execute whatever bytes
					; are loaded into the ram. Therefore
					; the normally defined bytes might
					; will be treated as instructions
					; This will cause the program to
					; crash. Note that here we are not
					; making any segmentation such as 
					; .data, .text, etc. Everything is 
					; just floating around as pure bytes
					; . Therefore the bios will think
					; of every byte as intruction
times 510 - ($-$$) db 0x00
dw 0xaa55
