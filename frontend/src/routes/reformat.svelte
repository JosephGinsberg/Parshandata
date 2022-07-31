<script lang="ts">
	// import { insert } from "svelte/internal";
	// import { tick } from 'svelte';

	let text: string = `{"array":["value1", "value2"],"object":{}}`

	function formatJSON(this: HTMLTextAreaElement, event: KeyboardEvent) {
		if (event.key !== '`') return
		event.preventDefault()

		const { selectionStart, selectionEnd, value } = this

		text = JSON.stringify(JSON.parse(value), undefined, 4)

		const wordsBeforeCursor: string = value.substring(selectionStart, 0).replace(/\n|\t|\s/g, '')
		let cursorStartPoint: number = 0,
			lettersPassed: number = 0
		for (const letter of text) {
			if (letter.match(/\n|\t|\s/)) {
				cursorStartPoint++
				continue
			}

			if (lettersPassed <= wordsBeforeCursor.length) lettersPassed++ && cursorStartPoint++
			else break
		}
		// console.log(wordsBeforeCursor.length, lettersPassed, cursorStartPoint, text.substring(cursorStartPoint, 0))

		setTimeout(() => {
			this.selectionStart = cursorStartPoint
			this.selectionEnd = cursorStartPoint + (selectionEnd - selectionStart)
		}, 20)

		// 		// text = value.replaceAll(/\s|\t|\n/g, '')

		// 		// last group type
		// 		// const groupType: string | undefined = value.substring(selectionStart, 0).match(/\[|\{/g)?.pop(),
		// 		// next group type
		// 		const groupType: string | undefined = value.substring(selectionStart).match(/\]|\}/g)?.[0],
		// 		cursorLocation: RegExpMatchArray | null =  value.substring(selectionStart).replaceAll(/\s|\t|\n/g, '').match(/(?<firstText>[^,:])?[,:\"\]\}](?<lastText>[^,:])?/)
		// 		let insertValue: string = '1738C40A_78DD_479B_A945_4D7F6398D9FX'

		// // [:]\s(\".*\")
		// // (?:\\[rnt]|:")(?<_KEY_1>[^="\\]+)=(?:\\")?(?<_VAL_1>[^="\\]+)

		// 		// handle pointer at end/between of array, object, key or pair (anywhere with \n\t+ inserted here)

		// 		// handles nested arrays in JSON
		// 		if( /*groupType===']' &&*/ ( cursorLocation?.[0][0]===']' || cursorLocation?.[0][0]===',' ))
		// 			insertValue = `,\"${insertValue}\"`
		// 		else if( /*groupType===']' &&*/ cursorLocation?.[0][0]==='\"' && cursorLocation?.groups?.lastText )
		// 			insertValue = `\"${insertValue}\",`

		// 		// handles (nested) objects in JSON
		// 		else if( false ) return

		// 		else if( false ) return

		// 		else if( !groupType )
		// 			return

		// 		// console.log( insertValue ) //, groupType, cursorLocation )

		// 		text = text.substring(selectionStart, 0) + insertValue + text.substring(selectionStart)
		// 		// console.log( text )
		// 		try{
		// 			text = JSON.stringify(JSON.parse(text), undefined, 4)
		// 		}catch{
		// 			return
		// 		}

		// 		if( insertValue[0]===',' ) insertValue = `,\n[\s\t]*${insertValue.substring(1)}`
		// 		else if( insertValue[0]==='\"' ) insertValue = `${insertValue}\n[ \t]*`
		// 		const regex = new RegExp(insertValue)
		// 		console.log(insertValue)// ,\n[\t\s]*"1738C40A_78DD_479B_A945_4D7F6398D9FX"
		// 		// console.log('Regex: ', regex, text.match(regex))// ,\n[\t\s]*"1738C40A_78DD_479B_A945_4D7F6398D9FX"

		// 		let splitChars: string[] = text.split(regex)
		// 		text = text.replace(regex, '')

		// 		setTimeout(() => {
		// 			this.selectionStart = splitChars[0].length
		// 			this.selectionEnd = splitChars[0].length + (selectionEnd - selectionStart)
		// 		}, 25);

		// if( groupType==='[' && cursorLocation?.[0][0]===']' ) insertValue = ','
		// else if( groupType==='{' && cursorLocation?.[0][0]===',' ) insertValue = ','

		// if( groupType && ( cursorLocation?.groups?.firstText || cursorLocation?.[0][0]==='\"' ) ) insertValue += '1738C40A_78DD_479B_A945_4D7F6398D9FX'
		// else if( groupType==='{' ) insertValue += '\"1738C40A_78DD_479B_A945_4D7F6398D9FX\":null'
		// else if( groupType===undefined ) return

		// if( groupType && cursorLocation?.[0][0]==='\"' ) insertValue = `\"${insertValue}\",`

		// let saveType: string = 'default',
		// formattedInput: string = ''

		// if(text.substring(selectionStart)[0] === ','){
		// 	saveType = 'value'
		// 	text = text.substring(selectionStart, 0) + ',\"unused\": \"|__________|\"' + text.substring(selectionStart)
		// }else{
		// 	// const savedLocation = text.substring(selectionStart, 0) + '|__________|' + text.substring(selectionStart)
		// 	// text = text.substring(selectionStart, 0) + '\"|__________|\"' + text.substring(selectionStart)
		// 	text = text.substring(selectionStart, 0) + '|__________|' + text.substring(selectionStart)
		// }

		// console.log(text)

		// try {
		// 	// consider using 'value' to track change location, instead of '|__________|'
		// 	// formattedInput = JSON.stringify(JSON.parse(value), undefined, 4)
		// 	formattedInput = JSON.stringify(JSON.parse(text), undefined, 4)
		// 	// formattedInput = JSON.stringify(JSON.parse(savedLocation), undefined, 4)
		// } catch {
		// 	console.error('Input is not valid JSON')
		// 	return
		// }

		// // if(text.substring(selectionStart-1, 1))
		// console.log(text.substring(selectionStart, 0), '|', text.substring(selectionStart)[0])

		// // let splitChars: string[]
		// if(saveType === 'value'){
		// 	splitChars = formattedInput.split(/,\n\s+\"unused\":\s?\"\|__________\|\"/)
		// 	text = formattedInput.replace(/,\n\s+\"unused\":\s?\"\|__________\|\"/, '')
		// 	// .replace(/,\n?\t*\"\|__________\|\"/, '')
		// }else{
		// 	splitChars = formattedInput.split('|__________|')
		// 	text = formattedInput.replace('|__________|', '')
		// }

		// setTimeout(() => {
		// 	this.selectionStart = splitChars[0].length
		// 	this.selectionEnd = splitChars[0].length + (selectionEnd - selectionStart)
		// }, 25);

		// const selection = value.slice(selectionStart, selectionEnd)

		// const replacement = /[a-z]/.test(selection)
		// 	? selection.toUpperCase()
		// 	: selection.toLowerCase();

		// text = (
		// 	value.slice(0, selectionStart) +
		// 	replacement +
		// 	value.slice(selectionEnd)
		// );

		// setTimeout(() => {
		// 	this.selectionStart = selectionStart
		// 	this.selectionEnd = selectionEnd
		// }, 25);
	}
</script>

<textarea bind:value={text} on:keydown={formatJSON} />

<style>
	textarea {
		display: block;
		margin: 50px auto;
		width: 500px;
		height: 200px;
	}
</style>
