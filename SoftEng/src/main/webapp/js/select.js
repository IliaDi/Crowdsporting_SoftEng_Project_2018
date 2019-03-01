var category = new SlimSelect({
  select: '#cat_multiple' ,

});

var tags_search = new SlimSelect({
  select: '#tags_multiple' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()

    // Optional - Return a valid data object. See methods/setData for list of valid options
    return {
      text: value,
      value: value.toLowerCase()
    }
  }
});

var provider = new SlimSelect({
  select: '#provider' ,

  addable: function (value) {
    // return false or null if you do not want to allow value to be submitted
    if (value === 'bad') {return false}

    // Return the value string
    return value // Optional - value alteration // ex: value.toLowerCase()

    // Optional - Return a valid data object. See methods/setData for list of valid options
    return {
      text: value,
      value: value.toLowerCase()
    }
  }
});

/*var select = new SlimSelect({
  select: '#select'
})
select.selected() // Will return a string or an array of string values*/
