import { writable } from 'svelte/store'

let books: string[] = ['Genesis', 'Exodus'],
searchJSON: SearchParam[] = [
	{
		param: 'input',
		type: 'letter',
		value: 'י',
		matchtype: "contains",
		count: 3,
		counttype: "equal",
		connector: 'none',
		level: 1
	}
],
searchRequest: SearchRequest = {
	books,
	useKeri: true,
	taamTachton: true,
	display: 'every',
	splitBy: 'word',
	search: searchJSON
},
searchStatus: SearchResponse | Promise<SearchResponse> = {
	ok: true,
	msg: '',
	runtime: 0,
	matches: [
		{
			bookname: 'Genesis',
			fullverse:
				'וַיַּ֣עַשׂ אֱלֹהִים֩ אֶת־חַיַּ֨ת הָאָ֜רֶץ לְמִינָ֗הּ וְאֶת־הַבְּהֵמָה֙ לְמִינָ֔הּ וְאֵ֛ת כָּל־רֶ֥מֶשׂ הָֽאֲדָמָ֖ה לְמִינֵ֑הוּ וַיַּ֥רְא אֱלֹהִ֖ים כִּי־טֽוֹב׃',
			pasuk: '25',
			perek: '1',
			splitvalue: 'לְמִינָ֗הּ'
		},
		{
			bookname: 'Exodus',
			fullverse:
				'וְאַחַ֗ר בָּ֚אוּ מֹשֶׁ֣ה וְאַהֲרֹ֔ן וַיֹּאמְר֖וּ אֶל־פַּרְעֹ֑ה כֹּֽה־אָמַ֤ר יְהוָה֙ אֱלֹהֵ֣י ',
			pasuk: '5',
			perek: '1',
			splitvalue: 'וַיֹּאמְר֖וּ'
		},
		{
			bookname: 'Leviticus',
			fullverse:
				'וְהִקְרִיב֞וֹ לִפְנֵ֤י יְהוָה֙ וְכִפֶּ֣ר עָלֶ֔יהָ וְטָהֲרָ֖ה מִמְּקֹ֣ר דָּמֶ֑יהָ זֹ֤את תּוֹרַת֙ הַיֹּלֶ֔דֶת לַזָּכָ֖ר א֥וֹ לַנְּקֵבָֽה',
			pasuk: '12',
			perek: '7',
			splitvalue: 'וְהִקְרִיב֞וֹ'
		},
		{
			bookname: 'Amos',
			fullverse:
				'דִּבְרֵ֣י עָמ֔וֹס אֲשֶׁר־הָיָ֥ה בַנֹּקְדִ֖ים מִתְּק֑וֹעַ אֲשֶׁר֩ חָזָ֨ה עַל־יִשְׂרָאֵ֜ל בִּימֵ֣י ׀ עֻזִיָּ֣ה',
			pasuk: '1',
			perek: '1',
			splitvalue: 'דִּבְרֵ֣י עָמ֔וֹס'
		}
	]
}

export const globalState = writable({
	searchRequest,
	searchStatus
})