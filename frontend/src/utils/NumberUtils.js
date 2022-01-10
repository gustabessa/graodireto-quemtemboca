export default {
  maskPhone (phone = '') {
    let unmaskedValue = String(phone)
    let maskedPhone = ''
    if (phone.length === 11) {
      maskedPhone += `(${unmaskedValue.slice(0, 2)}) ${unmaskedValue.slice(2, 3)} `
      unmaskedValue = unmaskedValue.slice(3)
    } else {
      maskedPhone += `(${unmaskedValue.slice(0, 2)}) `
      unmaskedValue = unmaskedValue.slice(2)
    }
    maskedPhone += `${unmaskedValue.slice(0, 4)}-${unmaskedValue.slice(4)}`

    return maskedPhone
  },

  formatMoneyBr (price) {
    return price.toLocaleString('pt-br', { style: 'currency', currency: 'BRL' })
  }
}
