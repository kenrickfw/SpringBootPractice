import { useState } from 'react'
import { useDispatch } from 'react-redux'
import { createRecord } from '../redux/recordSlice'

const RecordForm = () => {
  const dispatch = useDispatch()

  const [title, setTitle] = useState('')
  const [amount, setAmount] = useState('')
  const [date, setDate] = useState('')
  const [error, setError] = useState(null)
  const [emptyFields, setEmptyFields] = useState([])

  const handleSubmit = async (e) => {
    e.preventDefault()

    const record = { title, amount, owner: "Kenrick", date }
    
    const response = await fetch('http://localhost:8080/api/records', {
      method: 'POST',
      body: JSON.stringify(record),
      headers: {
        'Content-Type': 'application/json'
      }
    })
    const json = await response.json()

    if (!response.ok) {
      setError(json.error)
      setEmptyFields(json.emptyFields)
    }
    if (response.ok) {
      setEmptyFields([])
      setError(null)
      setTitle('')
      setAmount('')
      setDate('')
      dispatch(createRecord(json))
    }

  }

  return (
    <form className="create" onSubmit={handleSubmit}> 
      <h3>Add a New Record</h3>

      <label>Record Title:</label>
      <input 
        type="text" 
        onChange={(e) => setTitle(e.target.value)} 
        value={title}
        className={emptyFields.includes('title') ? 'error' : ''}
      />

      <label>Amount:</label>
      <input 
        type="number" 
        onChange={(e) => setAmount(e.target.value)} 
        value={amount}
        className={emptyFields.includes('amount') ? 'error' : ''}
      />

      <label>Date:</label>
      <input 
        type="date" 
        onChange={(e) => setDate(e.target.value)} 
        value={date}
        className={emptyFields.includes('date') ? 'error' : ''}
      />

      <button>Add Record</button>
      {error && <div className="error">{error}</div>}
    </form>
  )
}

export default RecordForm