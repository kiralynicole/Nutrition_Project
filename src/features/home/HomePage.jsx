import './HomePage.css'

export function HomePage(){
    return (
        <>
        <h1>Unlock Your Full Potential with Our Premium Protein and Health Products</h1>
        <div className='container'>
        <img src="https://media.istockphoto.com/id/1287093503/nl/vector/kleine-personages-drinking-protein-cocktails-van-shaker-sportieve-voeding-gezonde.jpg?s=612x612&w=0&k=20&c=e77-svXJ0_RnwoybiuJ_ae3ZDUAkSUqypsB84Ydhv94="></img>
        </div>

        <footer className='footer'>
                <div className='contact-info'>
                    <h2>Contact Us</h2>
                    <p>Email: contact@yahoo.com</p>
                    <p>Phone: +123 456 7890</p>
                </div>
                <div className='admin-info'>
                    <h2>About the Admin</h2>
                    <p>Name: John Doe</p>
                    <p>Role: Nutrition Specialist</p>
                </div>
                <div className='delivery-info'>
                    <h2>Delivery Information</h2>
                    <p>Enjoy free shipping on all orders over $200!</p>
                    <p>Standard delivery time: 3-5 business days.</p>
                    <p>Expedited options available.</p>
                </div>
            </footer>
        </>
    )
}